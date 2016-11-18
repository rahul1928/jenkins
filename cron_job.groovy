# this job start 8pm every day and run script 

job('cronjob8pm') {
   scm {
     git {
       remote {
         name('origin')
           url('')
           branch('*/master')
          }
   }
 }
           
     triggers {
       scm('* 20 * * *')
   }
 
  steps {
     shell("python -c 'from file  import *; print stop()'")
 }
 
 
}
