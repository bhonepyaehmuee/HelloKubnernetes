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
                    sh 'docker build -t bownoed/helloworld:v1.0 .'
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh "docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}"
                }
                sh 'docker push bownoed/helloworld:v1.0'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                 withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]){
                                          sh 'kubectl apply -f deployment.yaml'
                                          sh 'kubectl apply -f service.yaml'
                                        }
                                    }
                                }
            }
        }
//         stage('Deploy to Kubernetes') {
//                     steps {
//
//                 withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]){
//                           sh 'kubectl apply -f deployment.yaml'
//                           sh 'kubectl apply -f service.yaml'
//                         }
//                     }
//                 }
//     }

    post {
        success {
            echo "✅ Pipeline succeeded! App running at http://localhost:${DOCKER_HOST_PORT}/"
            emailext(
                to: 'bhshi75@gmail.com',
                subject: "Pipeline Success",
                body: "Pipeline succeeded. Your app is running at http://localhost:${DOCKER_HOST_PORT}/"
            )
        }
        failure {
            echo "❌ Pipeline failed."
            emailext(
                to: 'bhshi75@gmail.com',
                subject: "Pipeline Failure",
                body: "Pipeline failed. Please check the logs."
            )
        }
        always {
            echo "🏁 Pipeline finished."
        }
    }
}