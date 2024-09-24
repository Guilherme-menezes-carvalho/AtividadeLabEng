pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {

                git 'http://github.com/Guilherme-menezes-carvalho/AtividadeLabEng.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {

                bat 'mvn test'
            }
        }
    }
}
