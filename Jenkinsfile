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
        stage('Publish Gateway') {
            steps {
				dir('gateway') {
                   sh '''
                        docker tag esp31-gateway 192.168.160.99:5000/esp31-gateway
                        docker push 192.168.160.99:5000/esp31-gateway
                    '''
                }
            }
        }
        stage('Publish Webserver') {
            steps {
				dir('gateway') {
                   sh '''
                        docker tag esp31-webserver 192.168.160.99:5000/esp31-webserver
                        docker push 192.168.160.99:5000/esp31-webserver
                    '''
                }
            }
        }
    }
}
