pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Substitua o URL do repositório pelo correto
                git 'https://github.com/Guilherme-menezes-carvalho/AtividadeLabEng.git'
            }
        }
        stage('Build') {
            steps {
                // Executa o build com Maven no Windows
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                // Executa os testes com Maven no Windows
                bat 'mvn test'
            }
        }

        stage('Report Tests') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }

         stage('Docker Build') {
                steps {
                    script {
                        docker.build('pipelineDocker')
                    }
                }
            }

        stage('Docker Run') {
                steps {
                    script {
                        docker.image('pipelineDocker').run('-p 8083:8083')
                    }
                }
            }
    }

    post {
        success {
            echo 'Build and Deploy succeeded!'
        }
        failure {
            echo 'Build or Deploy failed!'
        }
    }
}
