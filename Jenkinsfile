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
        // Stage 1: Checkout code from Git
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/bhonepyaehmuee/HelloKubnernetes.git'
            }
        }

        // Stage 2: Build the JAR file
        stage('Build Jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        // Stage 3: Build the Docker Image
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t bownoed/helloworld:v1.0 .'
                }
            }
        }

        // Stage 4: Push Docker Image to Docker Hub
        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                script {
                                        sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                                        sh "docker push ${DOCKER_REPO}:v1.0"
                                    }
                 }
//                     sh "docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}"
//                 }
//                 sh 'docker push bownoed/helloworld:v1.0'
            }
        }

        // Stage 5: Deploy to Kubernetes
//         stage('Deploy to Kubernetes') {
//             steps {
//                 withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
//                     sh 'kubectl apply -f deployment.yaml --validate=false'
//                     sh 'kubectl apply -f service.yaml'
//                 }
//             }
//         }
        stage('Deploy with Ansible') {
                    steps {
                        sh 'ansible-playbook ansible/playbook.yml -i ansible/inventory'
                    }
                }
    }

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