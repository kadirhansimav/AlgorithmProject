# AlgorithmProject

Baran Sönmez 1905011157 
Batuhan Şahin 19050111051
Emre Özer 19050111053
Kadirhan Simav 19050111029
Tolga Şükrü Özkan 19050111031

In this project, we wrote an algorithm that solves a 2x2x2 rubik's cube.<br>
We use java maven_17 and java swing for gui. <br>

A cube is given in the photos below and each photo has 6 different faces of the cube, each face has a two possible moves. In total 12 different possible moves to solve cube. We determined the indexes of the probabilities that each face of the cube will rotate clockwise and counterclockwise.<br>
After that We use BFS algorithm solve the cube. <br>

Because the 2x2x2 Cube has no center, the front surfaces may not be the same for the base state and the solved state. This brings more than one solution. Thus, when we look at the projection of the solved cube, we can see the variants where the colors are in different places.<br>

<h1>How Can You Install And Run The Project
  
- Clone the repository
- You need the Java SDK 17
- You can run this project any IDE (There isn't any dependency in there)
- Also builded jar file in rubik/target named "rubik-1.0-SNAPSHOT.jar" 
- You can run this project "java -jar rubik-1.0-SNAPSHOT.jar"
  <br>
  
<h2>Javadoc Added





![F_FR](https://user-images.githubusercontent.com/91075579/213036372-3638d050-bb0d-46fe-8193-2d2b8e5b0acf.jpeg)
![B_BR](https://user-images.githubusercontent.com/91075579/213036377-f6ba6031-ceac-4c05-8740-2794ab94c94f.jpeg)
![U_UR](https://user-images.githubusercontent.com/91075579/213036382-0e8b5da4-6352-45af-a992-e55d703f7140.jpeg)
![D_DR](https://user-images.githubusercontent.com/91075579/213036391-3a29912c-2704-4c1e-ab51-108564eae766.jpeg)
![L_LR](https://user-images.githubusercontent.com/91075579/213036400-12959d12-648c-48c1-9ffb-63cde3a0b647.jpeg)
![R_RR](https://user-images.githubusercontent.com/91075579/213036413-0f0051ee-d975-44f0-bef9-4e6c881fb6b9.jpeg)
<img width="416" alt="base_state" src="https://user-images.githubusercontent.com/91075579/213219947-87731840-ecdc-4394-8c4e-a468e30d6bf5.png">
<img width="436" alt="random_solve_state" src="https://user-images.githubusercontent.com/91075579/213219961-79603dc3-7da6-48e3-9f0f-bb1e5037f0ea.png">
