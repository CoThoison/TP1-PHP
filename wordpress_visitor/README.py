#!/usr/bin/env python

# LOG6302A - Lab1 - AST & visitor examples

# Load AST

from code_analysis import AST, ASTReader
import glob

reader = ASTReader()

# Create a visitor that returns function definition position in source file


class ASTFunctionDefinitionVisitor:
    def __init__(self):
        self.ast = None

    def visit(self, ast: AST):
        self.ast = ast
        print(f"Visit AST from file {self.ast.get_filename()}")
        self.__visit(self.ast.get_root())

    def __visit(self, node_id: int):
        if self.ast.get_type(node_id) == "FunctionStatement":
            print(f"Function '{self.ast.get_image(node_id)}' definition is from "
                f"line {self.ast.get_position(node_id)[0]} to {self.ast.get_position(node_id)[1]}")

        for child_id in self.ast.get_children(node_id):
            self.__visit(child_id)

# create a visitor that returns SQL query position in source file

class ASTSQLQueryVisitor:
    def __init__(self):
        self.ast = None

    def visit(self, ast: AST):
        self.ast = ast
        print(f"Visit AST from file {self.ast.get_filename()}")
        self.__visit(self.ast.get_root())

    def __visit(self, node_id: int):
        if self.ast.get_type(node_id) == "FunctionCall" and self.ast.get_image(node_id) == "mysql_query":
            print(f"SQL query : \"mysql_query\" is called "
                f"at line {self.ast.get_position(node_id)[0]}")
        if self.ast.get_type(node_id) == "FunctionCall" and self.ast.get_image(node_id) == "mysqli_query":
            print(f"SQL query : \"mysqli_query\" is called "
                f"at line {self.ast.get_position(node_id)[0]}")
        if self.ast.get_type(node_id) == "FunctionCall" and self.ast.get_image(node_id) == "fsockopen":
            print(f"CVE 2017-7189 : \"fsockopen\" is called "
                f"at line {self.ast.get_position(node_id)[0]}")
        if self.ast.get_type(node_id) == "FunctionCall" and self.ast.get_image(node_id) == "filter_var" and self.ast.recursive_name_node("FILTER_VALIDATE_URL", node_id) :
            print(f"CVE 2021-21705 & 2020-7071 : \"filter_var\" is called with argument FILTER_VALIDATE_URL "
                f"at line {self.ast.get_position(node_id)[0]}")
        if self.ast.get_type(node_id) == "FunctionCall" and self.ast.get_image(node_id) == "openssl_encrypt":
            print(f"CVE-2020-7069 : \"openssl_encrypt\" is called "
                f"at line {self.ast.get_position(node_id)[0]}")
        if self.ast.get_type(node_id) == "FunctionCall" and self.ast.get_image(node_id) == "simplexml_load_file":
            print(f"CVE-2021-21707 : \"simplexml_load_file\" is called "
                f"at line {self.ast.get_position(node_id)[0]}")
        if self.ast.get_type(node_id) == "FunctionCall" and self.ast.get_image(node_id) == "mb_split":
            print(f"CVE-2019-9025 : \"mb_split\" is called "
                f"at line {self.ast.get_position(node_id)[0]}")
        if self.ast.get_type(node_id) == "FunctionCall" and self.ast.get_image(node_id) == "iconv_mime_decode_headers":
            print(f"CVE-2019-11039 : \"iconv_mime_decode_headers\" is called "
                f"at line {self.ast.get_position(node_id)[0]}")
        if self.ast.get_type(node_id) == "MethodCall" and self.ast.get_image(node_id) == "execute":
            print(f"SQL query : \"execute\" is called "
                f"at line {self.ast.get_position(node_id)[0]}")
        if self.ast.get_type(node_id) == "MethodCall" and self.ast.get_image(node_id) == "exec":
            if self.ast.get_children(self.ast.get_children(self.ast.get_parents(node_id)[0])[0]) != [] :
                if self.ast.get_image(self.ast.get_children(self.ast.get_children(self.ast.get_parents(node_id)[0])[0])[-1]) == "mysql":
                    print(f"SQL query : \"exec\" is called "
                        f"at line {self.ast.get_position(node_id)[0]}")

        for child_id in self.ast.get_children(node_id):
                self.__visit(child_id)

#for filename in glob.iglob("D:/Images/Cours/3A Poly/LOG6302A - Analyse d'application et perspective de cyber-sécurité/TP/TP1 source/TP1_LOG6308A/log6302a_lab1/code_to_analyze/test_cve" + '**/*.json', recursive=True):
for filename in glob.iglob("D:/Images/Cours/3A Poly/LOG6302A - Analyse d'application et perspective de cyber-sécurité/TP/TP1 source/TP1_LOG6308A/log6302a_lab1/code_to_analyze/wordpress_ast/wp-includes/SimplePie/Cache/**/*.json", recursive=True):
    ast = reader.read_ast(filename)
    #ast = reader.read_ast("../example/example_1.php.ast.json")
    #ast_2 = reader.read_ast("../example/example_2.php.ast.json")
    #ast_3 = reader.read_ast("../example/example_3.php.ast.json")

    # Access AST information

    '''
    ast.get_root()       # Return the root node ID
    ast.get_type(45)     # Return the type of node 45
    ast.get_image(45)    # Return the image of node 45
    ast.get_children(45) # Return the list of children
    ast.get_parent(45)   # Return the list of parents
    ast.get_position(45) # Return the position in source file as an
                        # array [line_begin, line_end, column_begin, column_end, token_begin, token_end]
    '''

    root = ast.get_root()
    #print(f"Root node ID is {root}")
    #print(f"Root type is {ast.get_type(root)}")

    #print(f"Node root children are {ast.get_children(root)}")
    #for node_id in ast.get_children(root):
        #print(f"Node type of {node_id} is {ast.get_type(node_id)}")
    #print("\n")


    # visitor = ASTFunctionDefinitionVisitor()
    # visitor.visit(ast)
    # print("\n")

    visitor = ASTSQLQueryVisitor()
    visitor.visit(ast)
    print("\n")