pipeline {
    agent any 

    stages {
       stage('Checkout Code') {
             steps {
                 git url: 'https://github.com/rajinderpalsingh2001/math-operations/', branch: 'master'
             }
         }
        stage('Build') {
            steps {
                sh 'mvn clean package' 
            }
        }
        stage('Test') { 
            steps {
                sh 'mvn test' 
            }
        }
    }
}