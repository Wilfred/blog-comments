# blog-comments

A simple blog comments tool. Basic and friendly, based on [Heroku's tutorial](http://devcenter.heroku.com/articles/clojure-web-application).

## Usage

Running:

1. Set database environment variable: `$ export DATABASE_URL=postgresql://localhost:5432/shouter`
2. Install dependencies: `$ lein deps`
3. If the table isn't yet set up: `$ lein run -m blog-comments.models.migration`
4. Start the server: `$ lein ring server`

This will reload most code, but not routes.

## Deployment

blog-comments is designed to run on Heroku. For the first deploy, do the following:

1. Create the app on heroku: `$ heroku create --stack cedar`
2. Add a shared database to the app: `$ heroku addons:add shared-database`
3. Deploy the code: `$ git push heroku master`
4. Create the database table: `$ heroku run lein run -m blog-comments.models.migration`
5. Start the web process: `$ heroku scale web=1`
6. Admire in the browser: `$ heroku open`

## License

Copyright (C) 2011 Wilfred Hughes

Distributed under the Eclipse Public License, the same as Clojure.
