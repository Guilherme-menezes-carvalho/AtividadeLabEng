pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "guilhermemenezesc/seu-app:${env.BUILD_ID}" // Ajuste o nome da sua imagem
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Guilherme-menezes-carvalho/AtividadeLabEng.git'
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

        stage('Build Docker Image') {
            steps {
                script {
                    // Comando para construir a imagem Docker usando o Dockerfile
                    bat 'docker build -t %DOCKER_IMAGE% .'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Comando para fazer login no Docker Hub e enviar a imagem
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-cred', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                        bat 'echo %DOCKER_PASSWORD% | docker login -u %DOCKER_USERNAME% --password-stdin'
                        bat 'docker push %DOCKER_IMAGE%'
                    }
                }
            }
        }
    }

    post {
        always {
            // Limpar imagens locais para evitar acúmulo
            bat 'docker rmi %DOCKER_IMAGE% || exit 0'
        }
    }
}
