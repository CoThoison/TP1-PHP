/* Generated By:JJTree: Do not edit this line. ASTExpressionStatement.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTExpressionStatement extends SimpleNode {
  public ASTExpressionStatement(int id) {
    super(id);
  }

  public ASTExpressionStatement(PHP p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(PHPVisitor visitor, int branchAdd) {

    visitor.visit(this, branchAdd);
  }
}
/* JavaCC - OriginalChecksum=e9371bc165ee7b7a5d8c9297738bbc62 (do not edit this line) */
