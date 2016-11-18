#  In this job dsl we are merge dev branch in the master branch

job('branch-merge-dsl') {

scm {
        git {
            remote {
                name('origin')
                url('git@github.com:rahul1928/hello-jenkins.git')
            }

            branch('*/dev')
            extensions {
                mergeOptions {
                    remote('origin')
                    branch('master')
                }
            }
        }
   }

  publishers {
        git {
            pushOnlyIfSuccess()
            pushMerge()
        }
    }


}
