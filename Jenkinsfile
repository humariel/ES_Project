pipeline {
	agent any

    stages {
        stage('Gateway Test') {
            steps {
				dir('gateway') {
                	sh 'mvn test'
				}
            }
        }
        stage('Webserver Test') {
            steps {
				dir('webserver') {
                	sh 'mvn test'
				}
            }
        }
        stage('Build Gateway') {
            steps {
				dir('gateway') {
                    sh 'docker build -t esp31-gateway .'
                }
            }
        }
        stage('Build Webserver') {
            steps {
				dir('webserver') {
                    sh 'docker build -t esp31-webserver .'
                }
            }
        }
    }
}
