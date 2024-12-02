# Stage 1: Install dependencies and build the Next.js app
FROM node:18 AS build

WORKDIR /app

COPY package.json package-lock.json ./

RUN npm install

COPY . .

RUN npm run build

# Stage 2: Serve the app in a production environment
FROM node:18-slim

WORKDIR /app

COPY --from=build /app . 

ENV NODE_ENV production

EXPOSE 3000

CMD ["npm", "start"]
