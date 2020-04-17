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
        stage('build') {
            steps {
				sh '$PWD'
            }
        }
    }
}
