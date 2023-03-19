***PLEASE CHECK***
- Input and output files should in the same directory of codes (project path\src\code)
- MAC user: please change path separators in the every main function like below
    (Because WINDOW and MAC path separators are different)

File file = new File(path + "/src/code/" + inputName);
FileReader file_reader = new FileReader(file);
OutputStream output = new FileOutputStream(path + "/src/code/" + outputName);

_____________________________________________________________________________________

Description of my optimized static evaluation function:
My new estimation function is better than the original one.
Because my function counts the number of mill, but original one just check the number of W's and B's number.
If we check the number of mills, we can know the better position so this heuristic will represent the good position than the original function.
