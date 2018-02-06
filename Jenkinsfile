pipeline {
  agent any
    
  stages {
    stage('Get Code ') {
      steps {
        git(url: 'https://github.com/phanees/chatapplication.git', branch: 'master')
      }
    }
    
    stage ('Test') {
      steps {
        sh './mvnw clean test'
      }
    }
        
    stage ('Install') {
      steps {
        sh './mvnw clean package dockerfile:build'
      }
    }
  }
}
