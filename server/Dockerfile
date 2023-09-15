#FROM node:14.4.0-alpine
FROM node:16-alpine

WORKDIR "/app"

COPY ./package.json ./

RUN npm install

COPY . .

CMD ["npm", "run", "start"]
