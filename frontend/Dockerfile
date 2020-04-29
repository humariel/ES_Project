# Stage 0, based on Node.js, to build and compile Angular
FROM node:8.11 as node

# create destination directory
RUN mkdir -p /usr/src/nuxt-app
WORKDIR /usr/src/nuxt-app

# copy the app, note .dockerignore
COPY . /usr/src/nuxt-app/
RUN npm install
RUN npm rebuild node-sass

# build necessary, even if no static files are needed,
# since it builds the server as well
RUN npm run build

# expose 5000 on container
EXPOSE 3000

# set app serving to permissive / assigned
ENV NUXT_HOST=0.0.0.0
# set app port
ENV NUXT_PORT=3000

# start the app
CMD [ "npm", "start" ]
