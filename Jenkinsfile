pipeline {
  agent any

  environment {
    APP_NAME = credentials('app-name')
    HEROKU_API_KEY = credentials('heroku-api-key')
    SONARQUBE_API_KEY = credentials('sonarqube-api-key')
  }

  stages {

    stage('Install Dependencies') {
      steps {
        sh 'bash ./scripts/install_dependencies.sh'
      }
    }

    stage('Run Tests') {
      steps {
        sh 'bash ./scripts/run_tests.sh'
      }
    }

    stage("SonarQube Analysis") {
      steps {
        withSonarQubeEnv('SonarQube') {
          sh 'bash ./scripts/static_code_analysis.sh'
        }
      }
    }

    stage("Quality Gate") {
      steps {
        timeout(time: 2, unit: 'MINUTES') {
          waitForQualityGate abortPipeline: true
        }
      }
    }

    stage('Build Image') {
      steps {
        sh 'bash ./scripts/build_docker_image.sh'
      }
    }

    stage('Release Image') {
      when {
        branch 'main'
      }
      steps {
        sh 'bash ./scripts/release_image.sh'
      }
    }
  }
}
