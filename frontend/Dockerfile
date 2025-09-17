# Build stage
FROM node:18-alpine AS build
WORKDIR /app
COPY skill-barter-web/package*.json ./
RUN npm ci
COPY skill-barter-web/ .
RUN npm run build -- --configuration=production

# Runtime stage
FROM nginx:alpine
COPY --from=build /app/dist/skill-barter-web /usr/share/nginx/html
EXPOSE 80
