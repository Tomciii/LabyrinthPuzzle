-- id, grid, isSolved
INSERT INTO EIGHT VALUES (1,"1,0,2,3,4,5,6,7,8",0);
INSERT INTO EIGHT VALUES (2,"1,2,0,3,4,5,6,7,8",0);
INSERT INTO EIGHT VALUES (3,"1,0,2,3,4,5,6,7,8",0);
INSERT INTO EIGHT VALUES (4,"1,0,2,3,4,5,6,7,8",0);
INSERT INTO EIGHT VALUES (5,"1,0,2,3,4,5,6,7,8",0);
INSERT INTO EIGHT VALUES (6,"1,0,2,3,4,5,6,7,8",0);
INSERT INTO EIGHT VALUES (7,"1,0,2,3,4,5,6,7,8",0);
INSERT INTO EIGHT VALUES (8,"1,0,2,3,4,5,6,7,8",0);
INSERT INTO EIGHT VALUES (9,"1,0,2,3,4,5,6,7,8",0);
INSERT INTO EIGHT VALUES (10,"1,0,2,3,4,5,6,7,8",0);
INSERT INTO EIGHT VALUES (11,"1,0,2,3,4,5,6,7,8",0);
INSERT INTO EIGHT VALUES (12,"1,0,2,3,4,5,6,7,8",0);

-- id, grid, isSolved
INSERT INTO MEMORY VALUES(1,"0,0,1,1,2,2",0);
INSERT INTO MEMORY VALUES(2,"0,0,1,1,2,2",0);
INSERT INTO MEMORY VALUES(3,"0,0,1,1,2,2",0);
INSERT INTO MEMORY VALUES(4,"0,0,1,1,2,2",0);
INSERT INTO MEMORY VALUES(5,"0,0,1,1,2,2",0);
INSERT INTO MEMORY VALUES(6,"0,0,1,1,2,2",0);
INSERT INTO MEMORY VALUES(7,"0,0,1,1,2,2",0);
INSERT INTO MEMORY VALUES(8,"0,0,1,1,2,2",0);
INSERT INTO MEMORY VALUES(9,"0,0,1,1,2,2",0);
INSERT INTO MEMORY VALUES(10,"0,0,1,1,2,2",0);

-- (labyrinthtile)id, labyrinthID, xCoordinate, yCoordinate, puzzleArchetypeID, up, down, left, right
INSERT INTO LABYRINTHTILE VALUES (1,0,0,0,1,1,0,0,0);
INSERT INTO LABYRINTHTILE VALUES (2,0,0,1,2,0,99,0,1);
INSERT INTO LABYRINTHTILE VALUES (3,0,1,1,2,0,0,99,2);
INSERT INTO LABYRINTHTILE VALUES (4,0,2,1,1,0,0,99,2);
INSERT INTO LABYRINTHTILE VALUES (5,0,3,1,1,0,0,99,3);
INSERT INTO LABYRINTHTILE VALUES (6,0,4,1,2,3,0,99,0);
INSERT INTO LABYRINTHTILE VALUES (7,0,4,2,1,5,99,0,0);
INSERT INTO LABYRINTHTILE VALUES (8,0,4,3,2,4,99,0,0);
INSERT INTO LABYRINTHTILE VALUES (9,0,4,4,1,6,99,7,0);
INSERT INTO LABYRINTHTILE VALUES (10,0,3,4,2,0,0,5,99);
INSERT INTO LABYRINTHTILE VALUES (11,0,2,4,1,8,9,10,99);
INSERT INTO LABYRINTHTILE VALUES (12,0,2,5,2,6,99,0,0);
INSERT INTO LABYRINTHTILE VALUES (13,0,2,6,1,11,99,0,0);
INSERT INTO LABYRINTHTILE VALUES (14,0,4,5,2,7,99,0,0);
INSERT INTO LABYRINTHTILE VALUES (15,0,2,3,2,99,8,0,0);
INSERT INTO LABYRINTHTILE VALUES (16,0,1,4,2,0,0,9,99);
INSERT INTO LABYRINTHTILE VALUES (17,0,0,4,1,12,0,0,99);
INSERT INTO LABYRINTHTILE VALUES (18,0,0,5,2,10,99,0,0);
