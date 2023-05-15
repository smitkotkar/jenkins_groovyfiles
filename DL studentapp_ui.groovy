pipeline {
    agent any
    stages {
        stage ('pull') {
            steps {
                git branch: 'main', url: 'https://github.com/smitkotkar/studentapp-ui.git'
            }
        }
        stage ('build') {
            steps {
                sh '/opt/apache-maven/bin/mvn clean package'
            }
        }
        // stage ('test') {
        //     steps {
        //         sh '/opt/apache-maven/bin/mvn sonar:sonar -Dsonar.projectKey=studentapp -Dsonar.host.url=http://172.31.31.108:9000 -Dsonar.6c2f4eb508f13bce46458baccaa055c639bcbd8e'
        //     }
        // }
        stage ('test-built') {
            steps {
                withSonarQubeEnv(installationName:'sonar-server' , credentialsId : 'sonar-token') {
                      sh '/opt/apache-maven/bin/mvn sonar:sonar -Dsonar.ProjectKey=studentapp'
                }  
            }
        }
        stage ('quality-gate') {
            steps {
                timeout(time: 10 , unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage ('deploy') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'tomcat-cred', path: '', url: 'http://13.250.116.200:8080')], contextPath: '/', war: '**/*.war'
            }
        }
    }
}