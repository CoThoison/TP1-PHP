/* Generated By:JJTree: Do not edit this line. ASTPrimaryExpression.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTPrimaryExpression extends SimpleNode {
  public ASTPrimaryExpression(int id) {
    super(id);
  }

  public ASTPrimaryExpression(PHP p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(PHPVisitor visitor, int branchAdd) {

    visitor.visit(this, branchAdd);
  }
}
/* JavaCC - OriginalChecksum=1b036e66dd5a255b2f93d979f70a5181 (do not edit this line) */
