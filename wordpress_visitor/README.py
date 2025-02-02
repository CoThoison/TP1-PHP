#!/usr/bin/env python

# LOG6302A - Lab1 - AST & visitor examples

# Load AST

from code_analysis import AST, ASTReader
import glob
import argparse
import sys

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

# Create a visitor that returns SQL query position in source file

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
        if self.ast.get_type(node_id) == "MethodCall" and self.ast.get_image(node_id) == "execute":
            print(f"SQL query : \"execute\" is called "
                f"at line {self.ast.get_position(node_id)[0]}")
        if self.ast.get_type(node_id) == "MethodCall" and self.ast.get_image(node_id) == "exec":
            print(f"SQL query : \"exec\" is called "
                f"at line {self.ast.get_position(node_id)[0]}")

        for child_id in self.ast.get_children(node_id):
                self.__visit(child_id)

# Create a visitor that returns CVE position in source file

class ASTCVEVisitor:
    def __init__(self):
        self.ast = None
    
    def visit(self, ast: AST):
        self.ast = ast
        print(f"Visit AST from file {self.ast.get_filename()}")
        self.__visit(self.ast.get_root())
    
    def __visit(self, node_id: int):
        if self.ast.get_type(node_id) == "FunctionCall" and self.ast.get_image(node_id) == "fsockopen":
            print(f"CVE 2017-7189 : \"fsockopen\" is called "
                f"at line {self.ast.get_position(node_id)[0]}")
        if self.ast.get_type(node_id) == "FunctionCall" and self.ast.get_image(node_id) == "filter_var":
            # Arguments of filter_var are the children of the ArgumentList node which is the former node of the function
            vars_id = [self.ast.get_children(node_id - 1)[0], self.ast.get_children(node_id - 1)[1]]
            if self.ast.get_image(vars_id[0]) == "FILTER_VALIDATE_URL" or self.ast.get_image(vars_id[1]) == "FILTER_VALIDATE_URL":
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

        for child_id in self.ast.get_children(node_id):
                self.__visit(child_id)


# Analyze ASTs in the specified root directory
parser = argparse.ArgumentParser(description="Analyze an Abstract Syntax Tree (AST) from a specified root directory.")
parser.add_argument('path', type=str, help="Absolute or relative path to the AST root directory to analyze")
if len(sys.argv) == 1:
    parser.print_help()
    sys.exit(1)
args = parser.parse_args()


for filename in glob.iglob(args.path + '**/*.json', recursive=True):
    ast = reader.read_ast(filename)
    root = ast.get_root()

    # AST Node visit by SQL query detection visitor
    visitor = ASTSQLQueryVisitor()
    visitor.visit(ast)
    # AST Node visit by function definition visitor
    visitor = ASTCVEVisitor()
    visitor.visit(ast)
    print("\n")