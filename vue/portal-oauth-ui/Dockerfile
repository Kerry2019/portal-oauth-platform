FROM nginx

RUN mkdir -p app/dist

WORKDIR /app

COPY ./dist/ /app/dist
COPY nginx.conf /app

RUN ls && rm -rf /usr/share/nginx/html/*  && cp -r dist/* /usr/share/nginx/html/ && cp nginx.conf /etc/nginx/nginx.conf && cat /etc/nginx/nginx.conf  && rm -rf /app

CMD ["nginx","-g","daemon off;"]
