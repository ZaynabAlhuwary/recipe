FROM openjdk:8
ADD target/recipe.jar recipe.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","recipe.jar"]