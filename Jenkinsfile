pipeline {
  agent any

  environment {
    DOCKER_IMAGE = 'sunilkumarnagar9929/dockertest'
    // KUBE_CONFIG = credentials('kubeconfig-credentials-id') // Optional: if using Jenkins credentials
  }

  stages {
    stage('Clone Repository') {
      steps {
        git branch: 'main', url: 'https://github.com/sunilkumarnagar/github-docker-k8s-jenkines.git'
      }
    }
    
    stage('Fatch') {
      steps {
        echo "Fatch from git"
      }
    }
  
    stage('Build Docker Image') {
      steps {
        bat 'docker build -t $DOCKER_IMAGE .'
      }
    }

    stage('Push Docker Image') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds-id', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
          bat '''
            echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
            docker push $DOCKER_IMAGE
          '''
        }
      }
    }

    stage('Deploy to Kubernetes') {
      steps {
        bat '''
          kubectl apply -f k8s/deployment.yaml
          kubectl apply -f k8s/service.yaml
        '''
      }
    }
  }
}
