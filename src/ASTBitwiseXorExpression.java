/* Generated By:JJTree: Do not edit this line. ASTBitwiseXorExpression.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTBitwiseXorExpression extends SimpleNode {
  public ASTBitwiseXorExpression(int id) {
    super(id);
  }

  public ASTBitwiseXorExpression(PHP p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(PHPVisitor visitor, Object data) {

    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=09e0f14facd98d59451a4e58d5d03114 (do not edit this line) */
