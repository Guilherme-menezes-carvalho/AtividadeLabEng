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
        stage('Package') {
            steps {
                // Empacota o projeto com Maven no Windows
                bat 'mvn package'
            }
        }
        stage('Deploy') {
            steps {
                // Coloque aqui seus comandos de deploy, por exemplo, usando SCP ou outra ferramenta
                bat 'copy target\\*.jar \\\\path\\to\\deploy\\'
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
