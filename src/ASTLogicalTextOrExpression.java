/* Generated By:JJTree: Do not edit this line. ASTLogicalTextOrExpression.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTLogicalTextOrExpression extends SimpleNode {
  public ASTLogicalTextOrExpression(int id) {
    super(id);
  }

  public ASTLogicalTextOrExpression(PHP p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(PHPVisitor visitor, int branchAdd) {

    visitor.visit(this, branchAdd);
  }
}
/* JavaCC - OriginalChecksum=47ce02f7ede75d9af726d6b0d21ae9e6 (do not edit this line) */
