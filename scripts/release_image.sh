echo $HEROKU_API_KEY | docker login --username=_ --password-stdin registry.heroku.com
docker tag moll-y/tour-of-heroes-backend:latest registry.heroku.com/$APP_NAME/web
docker push registry.heroku.com/$APP_NAME/web
heroku container:release web --app=$APP_NAME
docker logout
