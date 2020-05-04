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
                sshagent(credentials: ['esp31-deploy']){
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp31 192.168.160.103 docker ps -f ancestor=esp31-gateway -q -a | xargs --no-run-if-empty docker container stop"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp31 192.168.160.103 docker ps -f ancestor=esp31-gateway -q -a | xargs --no-run-if-empty docker container rm"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp31 192.168.160.103 docker pull 192.168.160.99:5000/esp31-gateway"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp31 192.168.160.103 docker run -p 3181:8081 192.168.160.99:5000/esp31-gateway"
                }
            }
        }
        stage('Deploy Webserver') {
            steps {
                sshagent(credentials: ['esp31-deploy']){
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp31 192.168.160.103 docker ps -f ancestor=esp31-webserver -q -a | xargs --no-run-if-empty docker container stop"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp31 192.168.160.103 docker ps -f ancestor=esp31-webserver -q -a | xargs --no-run-if-empty docker container rm"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp31 192.168.160.103 docker pull 192.168.160.99:5000/esp31-webserver"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp31 192.168.160.103 docker run -p 3180:8080 192.168.160.99:5000/esp31-webserver"
                }
            }
        }
        stage('Deploy Frontend') {
            steps {
                sshagent(credentials: ['esp31-deploy']){
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp31 192.168.160.103 docker ps -f ancestor=esp31-frontend -q -a | xargs --no-run-if-empty docker container stop"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp31 192.168.160.103 docker ps -f ancestor=esp31-frontend -q -a | xargs --no-run-if-empty docker container rm"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp31 192.168.160.103 docker pull 192.168.160.99:5000/esp31-frontend"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp31 192.168.160.103 docker run -p 3100:3000 192.168.160.99:5000/esp31-frontend"
                }
            }
        }
    }
}