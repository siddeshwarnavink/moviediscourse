<?php
 
namespace App\Models;
 
use Illuminate\Database\Eloquent\Model;
 
class Comment extends Model
{
    protected $table = 'comments';

    protected $fillable = ['commentText', 'creator', 'parent'];

    public function creator()
    {
        return $this->belongsTo(User::class, 'creator');
    }

    public function childComments()
    {
        return $this->hasMany(Comment::class, 'parent');
    }

    public function parentComment()
    {
        return $this->belongsTo(Comment::class, 'parent');
    }
}

