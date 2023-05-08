pipeline {
    agent any
    stages {
        stage ('pull') {
            steps {
                git branch: 'main', credentialsId: 'id_rsa', url: 'git@github.com:smitkotkar/jenkins_groovyfiles.git'
            }
        }
        stage ('build') {
            steps {
                mvn package
            }
        }
        stage ('test') {
            steps {
                echo 'test succeed'
            }
        }
        stage ('deploy') {
            steps {
                echo 'deploy succeed'
            }
        }
    }
}