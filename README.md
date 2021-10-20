To reproduce the issue with missing uri metric when using query params:
1. start telegraf container via `docker-compose -f docker-compose.telegraf.yml up` - collected metrics will be available in that console
2. Run App.java spring boot app- it will perform 2 requests to itself which fail with 404 (one using path param, the other - query param )
3. inspect telegraf logs available in terminal in which you ran docker-compose