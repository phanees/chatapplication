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
        bat 'mvn clean test'
      }
    }
        
    stage ('Build Docker Image') {
      steps {
        bat 'mvn clean package dockerfile:build'
      }
    }
    
      stage ('Push Docker Image') {
      steps {
        bat 'mvn dockerfile:push'
      }
    }
  }
}
