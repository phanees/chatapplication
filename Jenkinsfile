pipeline {
  
   agent any
    
  stages {
    stage('Get Code ') {
      steps {
        git(url: 'https://github.com/phanees/chatapplication.git', branch: 'master')
      }
    }
    
    stage ('Test Code') {
      def mvn_version = 'maven-3.5.2'
        withEnv( ["PATH+MAVEN=${tool mvn_version}/bin"] ) {
      steps {        
          sh 'mvn clean test'
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
        def mvn_version = 'maven-3.5.2'
        withEnv( ["PATH+MAVEN=${tool mvn_version}/bin"] ) {
          sh 'mvn clean package dockerfile:build'
        }
      }
    }
    
      stage ('Push Docker Image') {
      steps {
        def mvn_version = 'maven-3.5.2'
        withEnv( ["PATH+MAVEN=${tool mvn_version}/bin"] ) {
          sh 'mvn dockerfile:push'
        }
      }
    }
  }
}
