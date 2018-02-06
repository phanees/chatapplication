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
        bat 'mvnw clean test'
      }
    }
        
    stage ('Install') {
      steps {
        bat 'mvnw clean package dockerfile:build'
      }
    }
  }
}
