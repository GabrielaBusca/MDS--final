1. Sa se scrie un program care concateneaza continutul a n fisiere intr-un alt fisier.

#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<string.h>

int main (int argc, char *argv[]) {
     if (argc < 3) {
          fprintf (stderr, "Trebuie sa introduceti parametrii de forma f1 + .. + fn f \n");
          return 1;
     }
     int j;
     for (j = 1; j <= argc - 2; j++)
          if (j %2 == 0 && strcmp(argv[j], "+") != 0) {
               fprintf(stderr, "Trebuie sa introduceti parametrii de forma f1 + .. + fn f \n");
               return 1;
          }
     int dest = open(argv[argc - 1], O_WRONLY | O_CREAT | O_TRUNC, S_IRWXU | S_IRWXG | S_IRWXO);
     // descriptorul fisierului destinatie si drepturile sale
     if (dest == -1) {
          perror(argv[argc - 1]);
          return 1;
     }
     int cp; // descriptorul fisierului sursa
     int i;
     char c;
     for (i = 1; i < argc - 1; i += 2) {
          cp = open (argv[i], O_RDONLY); // fisierele se deschid pe rand
          if (cp != -1) {
               while (read(cp, &c, sizeof(char)))
                    write (dest, &c, sizeof(char));
               close(cp);
           } else { // fisierul nu s-a putut deschide
                perror(argv[i]);
           }
     close(dest);
     return 0;
}
               }
     int j;
     for (j = 1; j <= argc - 2; j++)
          if (j %2 == 0 && strcmp(argv[j], "+") != 0) {
               fprintf(stderr, "Trebuie sa introduceti parametrii de forma f1 + .. + fn f \n");
               return 1;
          }
     int dest = open(argv[argc - 1], O_WRONLY | O_CREAT | O_TRUNC, S_IRWXU | S_IRWXG | S_IRWXO);
     // descriptorul fisierului destinatie si drepturile sale
     if (dest == -1) {
          perror(argv[argc - 1]);
          return 1;
     }
     int cp; // descriptorul fisierului sursa
     int i;
     char c;
     for (i = 1; 