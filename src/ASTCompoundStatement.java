/* Generated By:JJTree: Do not edit this line. ASTCompoundStatement.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTCompoundStatement extends SimpleNode {
  public ASTCompoundStatement(int id) {
    super(id);
  }

  public ASTCompoundStatement(PHP p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(PHPVisitor visitor, int branchAdd) {

    visitor.visit(this, branchAdd);
  }
}
/* JavaCC - OriginalChecksum=475f4db25d94dba76a1bc619bb592b7f (do not edit this line) */
