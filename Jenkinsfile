pipeline {
  agent any

  environment {
    DOCKER_IMAGE = 'sunilkumarnagar9929/springapp:dockertest'
    KUBE_CONFIG = credentials('kubeconfig-credentials-id') // Optional: if using Jenkins credentials
  }

  stages {
    stage('Clone Repository') {
      steps {
        git 'https://github.com/sunilkumarnagar/github-docker-k8s-jenkines.git'
      }
    }

    stage('Build Docker Image') {
      steps {
        sh 'docker build -t $DOCKER_IMAGE .'
      }
    }

    stage('Push Docker Image') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds-id', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
          sh '''
            echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
            docker push $DOCKER_IMAGE
          '''
        }
      }
    }

    stage('Deploy to Kubernetes') {
      steps {
        sh '''
          kubectl apply -f k8s/deployment.yaml
          kubectl apply -f k8s/service.yaml
        '''
      }
    }
  }
}
