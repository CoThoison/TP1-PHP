/* Generated By:JJTree: Do not edit this line. ASTSelectionStatement.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTSelectionStatement extends SimpleNode {

  private int branchCounter = 0;

  public ASTSelectionStatement(int id) {
    super(id);
  }

  public ASTSelectionStatement(PHP p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(PHPVisitor visitor, int branchAdd) {

    visitor.visit(this, this.branchCounter + branchAdd);
  }

  public void increaseElseIfCounter () {
    this.branchCounter++;
  }

  public void decreaseSwitchCounter () {
    this.branchCounter--;
  }

}
/* JavaCC - OriginalChecksum=cb5e6b2241dc82f89a47130578a20c0c (do not edit this line) */
