/* Generated By:JJTree: Do not edit this line. ASTRelationalExpression.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTRelationalExpression extends SimpleNode {
  public ASTRelationalExpression(int id) {
    super(id);
  }

  public ASTRelationalExpression(PHP p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(PHPVisitor visitor, int branchAdd) {

    visitor.visit(this, branchAdd);
  }
}
/* JavaCC - OriginalChecksum=59246e852db1ea13589abc3df3e2dfb1 (do not edit this line) */
