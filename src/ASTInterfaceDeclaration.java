/* Generated By:JJTree: Do not edit this line. ASTInterfaceDeclaration.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTInterfaceDeclaration extends SimpleNode {
  public ASTInterfaceDeclaration(int id) {
    super(id);
  }

  public ASTInterfaceDeclaration(PHP p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(PHPVisitor visitor, int branchAdd) {

    visitor.visit(this, branchAdd);
  }
}
/* JavaCC - OriginalChecksum=ccc83970a9e89fbb8b3fa1c5c3e67428 (do not edit this line) */
