pipeline {
    agent any
    stages {
        stage ('pull') {
            steps {
                git branch: 'main', url: 'https://github.com/smitkotkar/studentapp-ui.git'
            }
        }
        // stage ('build') {
        //     steps {
        //         sh '/opt/apache-maven/bin/mvn clean package'
        //     }
        // }
        // stage ('test') {
        //     steps { 
        //           sh '/opt/apache-maven/bin/mvn sonar:sonar -Dsonar.ProjectKey=student-app' 
        //     }
        // }
        stage ('build-test') {
            steps {
                withSonarQubeEnv(installationName:'sonar-server',credentialsId: 'sonar-token') { 
                  sh '/opt/apache-maven/bin/mvn sonar:sonar -Dsonar.ProjectKey=student-app'
                }  
            }
        }
        stage ('quality-gate') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                     waitForQualityGate abortPipeline: true
                }
            }
        }
        stage ('deploy') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'tomcat-credentials', path: '', url: 'http://54.193.118.237:8080/')], contextPath: '/', war: '**/*.war'
            }
        }
    }
}