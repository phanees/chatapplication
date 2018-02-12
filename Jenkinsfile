pipeline {
  
   agent any
    
  stages {
    stage('Get Code ') {
      steps {
        git(url: 'https://github.com/phanees/chatapplication.git', branch: 'master')
      }
    }
    
    stage ('Test Code') {
        steps {  
          if(isUnix()){
            sh 'mvn clean test'
          }else{
            bat 'mvn clean test'
          }
        
      }
      post {
             always {
                 junit 'target/surefire-reports/*.xml'
             }
      }
    }
        
    stage ('Build Docker Image') {
      steps {
        if(isUnix()){
           sh 'mvn clean package dockerfile:build'
        }else{
          bat 'mvn clean package dockerfile:build'
        }        
      }
    }
    
      stage ('Push Docker Image') {
      steps {
         if(isUnix()){
           sh 'mvn dockerfile:push'
         }else{
           bat 'mvn dockerfile:push'
         }        
      }
    }
  }
}
