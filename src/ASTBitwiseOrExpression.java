/* Generated By:JJTree: Do not edit this line. ASTBitwiseOrExpression.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTBitwiseOrExpression extends SimpleNode {
  public ASTBitwiseOrExpression(int id) {
    super(id);
  }

  public ASTBitwiseOrExpression(PHP p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(PHPVisitor visitor, Object data) {

    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=3f77ff3cffa7dfe662140a79e75f161e (do not edit this line) */
