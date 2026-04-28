pipeline {
    agent any

    tools {
        maven "maven3.9"
    }

    environment {
        DOCKER_REPO = "bownoed/helloworld"
        DOCKER_HOST_PORT = "9096"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/bhonepyaehmuee/HelloKubnernetes.git'
            }
        }


        stage('Build Jar') {
            steps {
                // Jar build AFTER coverage
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                sh 'docker build -t bownoed/helloworld:v1.0 .
'
                }
            }
        }
        stage('Push to Docker Hub') {
                    steps {
                       sh 'docker login -u $USER -p $PASS'
                       sh 'docker push bph/helloworld:v1.0'
                    }
        }
         stage('Deploy to Kubernetes') {
                   steps {
                        sh '''
                        kubectl apply -f deployment.yaml
                        kubectl apply -f service.yaml
                        '''
                    }
         }

    post {
        success {
            echo "✅ Pipeline succeeded! App running at http://localhost:${DOCKER_HOST_PORT}/"
            emailext(
                to: 'bhshi75@gmail.com',
                subject: 'Pipeline Email Test',
                body: 'Pipeline Success email sent successfully ✅'
            )
        }
        failure {
            echo "❌ Pipeline failed."
            emailext(
                to: 'bhshi75@gmail.com',
                subject: 'Pipeline Email Test',
                body: 'Pipeline Fail email sent successfully ✅'
            )
        }
        always {
            echo "🏁 Pipeline finished."
        }
    }
}
