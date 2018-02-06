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
        bat 'mvn clean test'
      }
    }
        
    stage ('Install') {
      steps {
        bat 'mvn clean package dockerfile:build'
      }
    }
  }
}
