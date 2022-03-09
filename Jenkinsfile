pipeline {
  agent any
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  environment {
    HEROKU_API_KEY = credentials('tour-of-heroes-backend-api-key')
    SONARQUBE_API_KEY = credentials('tour-of-heroes-backend-sonarqube-api-key')
  }
  parameters { 
    string(name: 'APP_NAME', defaultValue: '', description: 'What is the Heroku app name?') 
  }
  stages {
    stage('Install dependencies') {
      steps {
        sh 'mvn install -DskipTests'
      }
    }
    stage('Static code analysis') {
      steps {
        sh 'mvn sonar:sonar -Dsonar.login=$SONARQUBE_API_KEY'
      }
    }
    stage('Tests') {
      environment {
        SPRING_PROFILES_ACTIVE = 'dev'
      }
      steps {
        sh 'mvn test'
      }
    }
    stage('Build') {
      steps {
        sh 'docker build -t moll-y/tour-of-heroes-backend:latest .'
      }
    }
    stage('Release the image') {
      steps {
        sh '''
          echo $HEROKU_API_KEY | docker login --username=_ --password-stdin registry.heroku.com
          docker tag moll-y/tour-of-heroes-backend:latest registry.heroku.com/$APP_NAME/web
          docker push registry.heroku.com/$APP_NAME/web
          heroku container:release web --app=$APP_NAME
        '''
      }
    }
  }
  post {
    always {
      sh 'docker logout'
    }
  }
}
