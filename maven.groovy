mavenJob('Build_VMS') {
    logRotator {
    daysToKeep(30)
    numToKeep(15)
    }
    parameters {
        activeChoiceParam('CHOICE-1') {
            description('Allows user choose from multiple choices')
            filterable()
            choiceType('SINGLE_SELECT')
            groovyScript {
                script('proc1 = [\'/bin/bash\', \'-c\', \"/usr/bin/git ls-remote -h git-url\"].execute()\n'+
                       'proc2 = [\'/bin/bash\', \'-c\', \"awk \'{print \\$2}\' \"].execute()\n'+
                       'proc3 = [\'/bin/bash\', \'-c\', \"sed s%^refs/heads%origin%\"].execute()\n'+
                      'all = proc1 | proc2 | proc3\n'+
                      'String result = all.text\n'+
                     'String filename = \"/tmp/branches.txt\"\n'+
                     'boolean success = new File(filename).write(result)\n'+
                     'def multiline = \"cat /tmp/branches.txt\".execute().text\n'+ 
                     'def list = multiline.readLines()\n')
                fallbackScript('')
              
            }
        }
    }

     scm {
        git {
            remote {
                name('origin')
                url('')
            }
  
     }
     
   }     
  configure {
    it / publishers / 'org.jfrog.hudson.ArtifactoryRedeployPublisher' {
      evenIfUnstable 'false'
    
    }
    
  }
 
  publishers {
        extendedEmail {
            recipientList('')
            defaultSubject('')
            defaultContent('')
            contentType('text/html')
            triggers {
                beforeBuild()
                stillUnstable {
                    subject('Subject')
                    content('Body')
                    sendTo {
                        developers()
                        requester()
                        culprits()
                    }
                }
            }
        }
    }
  
}

