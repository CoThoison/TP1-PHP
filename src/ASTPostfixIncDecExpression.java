/* Generated By:JJTree: Do not edit this line. ASTPostfixIncDecExpression.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTPostfixIncDecExpression extends SimpleNode {
  public ASTPostfixIncDecExpression(int id) {
    super(id);
  }

  public ASTPostfixIncDecExpression(PHP p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(PHPVisitor visitor, Object data) {

    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=c686f021781d14bf169c69a6b3b99bb8 (do not edit this line) */
