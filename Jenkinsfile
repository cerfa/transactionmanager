pipeline {
    agent none
  options { skipDefaultCheckout() }
    stages {
	       stage('clean') {
            agent any
            steps {
                sh 'mvn clean'
            }
        }
        stage('validate') {
            agent any
            steps {
                sh 'mvn validate'
            }
        }
        stage('compile') {
            agent any
            steps {
                sh 'mvn compile'
            }
        }
        stage('package') {
            agent any
            steps {
                sh 'mvn package'
               }
            }
        stage('install') {
            agent any
            steps {
                sh 'mvn install'
	       }
           			
            post {
               success { 
                    sh 'echo done'
                }
		}
	}	
      }
    }
