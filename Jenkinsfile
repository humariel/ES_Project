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
        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    sh 'docker build -t esp31-frontend .'
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
                dir('webserver') {
                   sh '''
                        docker tag esp31-webserver 192.168.160.99:5000/esp31-webserver
                        docker push 192.168.160.99:5000/esp31-webserver
                    '''
                }
            }
        }
        stage('Publish Frontend') {
            steps {
                dir('frontend') {
                   sh '''
                        docker tag esp31-frontend 192.168.160.99:5000/esp31-frontend
                        docker push 192.168.160.99:5000/esp31-frontend
                    '''
                }
            }
        }
        stage('Deploy Gateway') {
            steps {
                dir('gateway') {
                    sh '''
                        docker stop (docker ps --filter ancestor=esp31-gateway -q -a)
                        docker rm (docker ps --filter ancestor=esp31-gateway -q -a)
                        docker pull 192.168.160.99:5000/esp31-gateway
                        docker run -p 3181:8081 192.168.160.99:5000/esp31-gateway
                    '''
                }
            }
        }
        stage('Deploy Webserver') {
            steps {
                dir('webserver') {
                    sh '''
                        docker stop (docker ps --filter ancestor=esp31-webserver -q -a)
                        docker rm (docker ps --filter ancestor=esp31-webserver -q -a)
                        docker pull 192.168.160.99:5000/esp31-webserver
                        docker run -p 3180:8080 192.168.160.99:5000/esp31-webserver
                    '''
                }
            }
        }
        stage('Deploy Frontend') {
            steps {
                dir('frontend') {
                    sh '''
                        docker stop (docker ps --filter ancestor=esp31-frontend -q -a)
                        docker rm (docker ps --filter ancestor=esp31-frontend -q -a)
                        docker pull 192.168.160.99:5000/esp31-frontend
                        docker run -p 3100:3000 192.168.160.99:5000/esp31-frontend
                    '''
                }
            }
        }
    }
}
